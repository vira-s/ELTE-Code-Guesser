package hu.edu.elte.codeguesser_model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * @author virabia on 5/10/2018
 */
public class CodeModel {

    protected final String code;

    public CodeModel(String code) {
        Assert.isTrue(StringUtils.isNotBlank(code), "The code must not be empty.");

        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
