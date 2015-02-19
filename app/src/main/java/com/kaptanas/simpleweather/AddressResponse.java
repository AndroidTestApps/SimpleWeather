package com.kaptanas.simpleweather;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by cihankaptan on 19/02/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AddressResponse implements Serializable{
    private static final long serialVersionUID = -3685285728430883572L;


}
