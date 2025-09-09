package com.knock.routingrules.dto;

import com.knock.routingrules.model.enums.CompanyIndustry;
import com.knock.routingrules.model.enums.ContactDevice;
import com.knock.routingrules.validation.IsEnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ContactDto {
    @Pattern(regexp = "^[A-Z]{2}$", message = "Contact country must be a valid ISO country code")
    private String contactCountry;
    
    @Positive(message = "Company size must be positive")
    private Integer companySize;
    
    @Pattern(regexp = "^[A-Z]{2}$", message = "Company HQ country must be a valid ISO country code")
    private String companyHqCountry;
    
    @IsEnumValue(enumClass = CompanyIndustry.class, message = "Company industry must be a valid industry type")
    @Schema(type = "string", description = "Company industry type", example = "AIRLINES_AVIATION")
    private CompanyIndustry companyIndustry;
    
    @Size(max = 255, message = "Company name must not exceed 255 characters")
    private String companyName;
    
    @IsEnumValue(enumClass = ContactDevice.class, message = "Contact device must be a valid device type")
    @Schema(type = "string", description = "Contact device type", example = "DESKTOP")
    private ContactDevice contactDevice;
    
    @Pattern(regexp = "^https?://.*", message = "First page must be a valid URL")
    private String firstPage;
    
    private LocalDateTime firstSeen;
    private LocalDateTime lastSeen;
}