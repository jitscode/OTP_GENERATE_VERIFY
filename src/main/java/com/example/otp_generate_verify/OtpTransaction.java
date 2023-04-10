package com.example.otp_generate_verify;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OtpTransaction {
    @Id
    private String email;
    private String otpkey;
    private int otp;

    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtpkey() {
        return otpkey;
    }

    public void setOtpkey(String otpkey) {
        this.otpkey = otpkey;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }
}
