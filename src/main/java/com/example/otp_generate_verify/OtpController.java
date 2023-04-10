package com.example.otp_generate_verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class OtpController {
    @Autowired
    OtpTransactionRepo repo;
    @Value("${otp.mail.subject}")
    private String mailSubject;

    @Autowired
    private MailService mailService;

    @PostMapping("/generate-otp")
    public String generateOTP(@RequestParam("email") String email) {
        String otpKey = OtpUtils.generateOTPKey();
        int otp = OtpUtils.generateAndCacheOTP(otpKey);
        String message = "Your OTP is: " + otp +" And Key is " +otpKey ;
        mailService.sendEmail(email, mailSubject, message);
        OtpTransaction transaction=new OtpTransaction();
        transaction.setEmail(String.valueOf(email));
        transaction.setOtp(otp);
        transaction.setOtpkey(otpKey);
        transaction.setStatus("Unverified");
        repo.save(transaction);
        return "OTP generated and sent to email";
    }
    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestBody OtpTransaction txn) {
        boolean isValid = OtpUtils.verifyOTP(txn.getOtpkey(), txn.getOtp());
        if (isValid) {
            OtpTransaction transaction=repo.findById(txn.getEmail()).orElse(null);
            transaction.setStatus("Verified");
            repo.save(transaction);
            mailService.sendEmail(txn.getEmail(), "Verification Successful", "OTP verification successful");
            return "OTP verification successful";
        } else {
            OtpTransaction transaction=repo.findById(txn.getEmail()).orElse(null);
            transaction.setStatus("Unverified");
            repo.save(transaction);
            mailService.sendEmail(txn.getEmail(), "Verification Failed", "OTP verification Failed");
            return "OTP verification failed";
        }
    }
    @GetMapping("/otptxn")
    public OtpTransaction alltxns(){
        return (OtpTransaction) repo.findAll();
    }
}
