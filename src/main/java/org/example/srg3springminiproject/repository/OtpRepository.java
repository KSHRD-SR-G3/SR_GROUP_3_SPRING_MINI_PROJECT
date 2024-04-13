package org.example.srg3springminiproject.repository;

import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.Otp;

@Mapper
public interface OtpRepository {
    @Results(id = "OtpMapper", value = {
            @Result(property = "id",column = "opt_id"),
            @Result(property = "otpCode", column = "otp_code"),
            @Result(property = "expirationTime", column = "expiration_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "verified", column = "verified"),
    })
    @Select("SELECT * FROM otp_tb WHERE user_id = #{userId} ORDER BY expiration_time DESC LIMIT 1 ")
    Otp getOtpByUserId(@Param("userId") Long userId);

    @Select("INSERT INTO otp_tb (otp_code, expiration_time, user_id) " +
            "VALUES (#{otp.otpCode}, #{otp.expirationTime}, #{otp.userId}) RETURNING *")
    @ResultMap("OtpMapper")
    Otp insertOtp(@Param("otp") Otp otp);

    @Select("UPDATE otp_tb SET verified = #{otpId.verified}, otp_code = #{otpId.otpCode}, expiration_time = #{otpId.expirationTime} WHERE opt_id = #{otpId.id} RETURNING *")
    @ResultMap("OtpMapper")
    Otp updateOtp(@Param("otpId") Otp otpId);

    @Select("SELECT * FROM otp_tb WHERE otp_code = #{otpCode} AND verified = false ORDER BY expiration_time DESC LIMIT 1")
    @ResultMap("OtpMapper")
    Otp getLatestOtpByCode(String otpCode);

    @Select("SELECT * FROM otp_tb WHERE user_id = (SELECT user_id FROM users_tb WHERE email = #{email}) AND verified = false ORDER BY expiration_time DESC LIMIT 1")
    @ResultMap("OtpMapper")
    Otp getLatestUnverifiedOtpByEmail(String email);
}
