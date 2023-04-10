package com.example.otp_generate_verify;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OtpTransactionRepo extends JpaRepository<OtpTransaction,String> {

}
