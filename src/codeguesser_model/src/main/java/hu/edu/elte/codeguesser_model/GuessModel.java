package hu.edu.elte.codeguesser_model;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author virabia on 5/12/2018
 */
public class GuessModel extends CodeModel {

    private List<GuessDigitStatusEnum> digitStatusList;

    public GuessModel(String code) {
        super(code);
        initDigitStatusList(code.length());
    }

    public GuessModel(String code, List<GuessDigitStatusEnum> digitStatusList) {
        super(code);

        Assert.isTrue(!Objects.isNull(digitStatusList) && digitStatusList.size() == code.length(),
                "The status list must contain the same number of entries as the code's length: "
                        + code.length() + ". " + digitStatusList);

        this.digitStatusList = new ArrayList<>(digitStatusList);
    }

    private void initDigitStatusList(int length) {
        this.digitStatusList = new ArrayList<>(Collections.nCopies(length, GuessDigitStatusEnum.UNKNOWN));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        GuessModel that = (GuessModel) other;
        return Objects.equals(code, that.code)
                && Objects.equals(digitStatusList, that.digitStatusList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, digitStatusList);
    }

    @Override
    public String toString() {
        return "GuessModel{"
                + "code=\'" + code + '\''
                + "digitStatusList=" + digitStatusList
                + '}';
    }
}
