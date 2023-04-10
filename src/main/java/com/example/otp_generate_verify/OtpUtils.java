package com.example.otp_generate_verify;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class OtpUtils {
    private static final int EXPIRE_MINUTES = 5;
    private static final int OTP_LENGTH = 6;
    private static final LoadingCache<String, Integer> otpCache;

    static {
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MINUTES, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }

    private static int generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return otp;
    }

    public static int getOTP(String key) {
        int otp = 0;
        try {
            otp = otpCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return otp;
    }

    public static void clearOTP(String key) {
        otpCache.invalidate(key);
    }

    public static boolean verifyOTP(String key, int otp) {
        int cacheOTP = getOTP(key);
        return cacheOTP == otp;
    }

    public static String generateOTPKey() {
        Random random = new Random();
        String key = String.format("%04d", random.nextInt(10000));
        return key;
    }

    public static void cacheOTP(String key, int otp) {
        otpCache.put(key, otp);
    }

    public static int generateAndCacheOTP(String key) {
        int otp = generateOTP();
        cacheOTP(key, otp);
        return otp;
    }
}
