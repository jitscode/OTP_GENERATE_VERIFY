# OTP_GENERATE_VERIFY
This is a JAVA SpringBoot REST API Application used for OTP_Generation And OTP_Verification with JAVA Mail Sender functionality. And it can also store the transactions of OTP's in MySql Database. Used Google guava for otp generation and verification by Caching not by storing it.


Methods Of Using the Rest API:

>OTP GENERATION:-

Method: POST
URL: http://localhost:8080/generate-otp?email=your@gmail.com

>OTP VERIFICATION:-

Method: POST
URL: http://localhost:8080/verify-otp
BODY (Content Type- JSON)-
{
    "email": "your@gmail.com",
    "otp": "1234",
    "otpkey": "5678"
}


**NOTE**
otpkey will also be sent to mail.
