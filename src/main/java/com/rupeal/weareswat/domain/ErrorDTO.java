package com.rupeal.weareswat.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Class for the errors within the REST requests
 */
@Builder
@Data
public class ErrorDTO {

    private String internalMessage;
    private String moreInfo;
    private Date dateTime;
}
