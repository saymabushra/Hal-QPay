package com.haltechbd.app.android.qpay.utils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class GetThana {
	public static String getThana(String strEncryptAccountNumber, String strEncryptPin, String strDistricId) {
		String METHOD_NAME = "QPAY_KYC_Get_Thana";
		String SOAP_ACTION = "http://www.bdmitech.com/m2b/QPAY_KYC_Get_Thana";
		SoapObject request = new SoapObject(GlobalData.getStrNamespace().replaceAll(" ","%20"), METHOD_NAME);

		// Declare the version of the SOAP request
		final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

//		QPAY_KYC_Get_Thana
//		AccountNo
//		PIN
//		DistrictID
//		strMasterKey

		PropertyInfo encryptAccountNumber = new PropertyInfo();
		encryptAccountNumber.setName("E_AccountNo");
		encryptAccountNumber.setValue(strEncryptAccountNumber);
		encryptAccountNumber.setType(String.class);
		request.addProperty(encryptAccountNumber);

		PropertyInfo encryptPin = new PropertyInfo();
		encryptPin.setName("E_PIN");
		encryptPin.setValue(strEncryptPin);
		encryptPin.setType(String.class);
		request.addProperty(encryptPin);

		PropertyInfo encryptDistrictId = new PropertyInfo();
		encryptDistrictId.setName("E_DistrictID");
		encryptDistrictId.setValue(strDistricId);
		encryptDistrictId.setType(String.class);
		request.addProperty(encryptDistrictId);

		PropertyInfo masterKey = new PropertyInfo();
		masterKey.setName("UserID");
		masterKey.setValue(GlobalData.getStrUserId());
		masterKey.setType(String.class);
		request.addProperty(masterKey);


		PropertyInfo device = new PropertyInfo();
		device.setName("DeviceID");
		device.setValue(GlobalData.getStrDeviceId());
		device.setType(String.class);
		request.addProperty(device);

		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);
		envelope.implicitTypes = true;
		Object objServerResponse = null;
		String strServerResponse = "";

		try {
			HttpTransportSE androidHttpTransport = new HttpTransportSE(GlobalData.getStrUrl().replaceAll(" ","%20"), 1000);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			objServerResponse = envelope.getResponse();
			strServerResponse = objServerResponse.toString();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return strServerResponse;
	}
}
