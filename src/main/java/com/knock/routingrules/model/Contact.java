package com.knock.routingrules.model;

import com.knock.routingrules.model.enums.CompanyIndustry;
import com.knock.routingrules.model.enums.ContactDevice;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Contact {
    private String contactCountry;
    private Integer companySize;
    private String companyHqCountry;
    private CompanyIndustry companyIndustry;
    private String companyName;
    private ContactDevice contactDevice;
    private String firstPage;
    private LocalDateTime firstSeen;
    private LocalDateTime lastSeen;
}