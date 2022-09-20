package one.microstream.storage;

import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class S3Utils
{
	private static S3Client AWSS3Alient;
	
	static {
		System.setProperty("aws.accessKeyId", "enter aws s3 access key here");
		System.setProperty("aws.secretAccessKey", "enter aws s3 secret key here");
		
		SystemPropertyCredentialsProvider credentialsProvider = SystemPropertyCredentialsProvider.create();
		
		AWSS3Alient = S3Client.builder().region(Region.EU_CENTRAL_1).credentialsProvider(credentialsProvider).build();
	}

	public static S3Client getClient()
	{
		return AWSS3Alient;
	}
	
}
