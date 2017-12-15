package com.amazonaws.samples;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class Sms {

	public static void main(String[] args) {
		
		Map<String, MessageAttributeValue> smsAttributes =
		        new HashMap<String, MessageAttributeValue>();
		smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
		        .withStringValue("HOF") //The sender ID shown on the device.
		        .withDataType("String"));
		smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
		        .withStringValue("0.05") //Sets the max price to 0.50 USD.
		        .withDataType("Number"));
		smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
		        .withStringValue("Promotional") //Sets the type to promotional.
		        .withDataType("String"));
		
		AmazonSNSClientBuilder snsClientBuilder = AmazonSNSClientBuilder.standard()
	            .withRegion(Regions.US_EAST_1)
	            .withCredentials(new ProfileCredentialsProvider());
		
		 AmazonSNSClient snsClient = (AmazonSNSClient) snsClientBuilder.build();
	        String message = "Hola http://bit.ly/2zJF4I0";
	        String phoneNumber = "+5491177629405";
	        
	        sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);

	}
	
	public static void sendSMSMessage(AmazonSNS snsClient, String message, 
			String phoneNumber, Map<String, MessageAttributeValue> smsAttributes) {
	        PublishResult result = snsClient.publish(new PublishRequest()
	                        .withMessage(message)
	                        .withPhoneNumber(phoneNumber)
	                        .withMessageAttributes(smsAttributes));
	        System.out.println(result); // Prints the message ID.
	}

}
