package com.thoughtworks.capability.gtb.restfulapidesign.persistence.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentPO {
    private Integer id;
    private String name;
    private String gender;
    private String note;
}
