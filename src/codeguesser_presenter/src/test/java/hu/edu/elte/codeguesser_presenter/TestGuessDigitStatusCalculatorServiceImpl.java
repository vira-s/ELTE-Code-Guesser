package hu.edu.elte.codeguesser_presenter;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import hu.edu.elte.codeguesser_presenter.services.GuessDigitStatusCalculatorServiceImpl;
import hu.edu.elte.codeguesser_model.GuessDigitStatusEnum;
import hu.edu.elte.codeguesser_presenter.utils.GameMode;

public class TestGuessDigitStatusCalculatorServiceImpl {
    private GuessDigitStatusCalculatorServiceImpl result = new GuessDigitStatusCalculatorServiceImpl();

    @Test(expected = IllegalArgumentException.class)
    public void testBlankInput() {
        result.calculateGuessDigitStatus(GameMode.EASY, "123", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTextInput() {
        result.calculateGuessDigitStatus(GameMode.EASY, "123", "a16");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShorterInput() {
        result.calculateGuessDigitStatus(GameMode.EASY, "1234", "123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongerInput() {
        result.calculateGuessDigitStatus(GameMode.EASY, "123", "1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBeginsWithZero() {
        result.calculateGuessDigitStatus(GameMode.EASY, "123", "016");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegative() {
        result.calculateGuessDigitStatus(GameMode.EASY, "123", "-16");
    }

    @Test
    public void testEasyNotContainingAny() {
        List<GuessDigitStatusEnum> easyNotContainingAny = new ArrayList<>();
        easyNotContainingAny.add(GuessDigitStatusEnum.WRONG);
        easyNotContainingAny.add(GuessDigitStatusEnum.WRONG);
        easyNotContainingAny.add(GuessDigitStatusEnum.WRONG);
        assertEquals(easyNotContainingAny, result.calculateGuessDigitStatus(GameMode.EASY, "123", "456"));
    }

    @Test
    public void testEasyContainingSome1() { // contains 1st digit
        List<GuessDigitStatusEnum> easyContainingSome = new ArrayList<>();
        easyContainingSome.add(GuessDigitStatusEnum.CONTAINING_NUMBER);
        easyContainingSome.add(GuessDigitStatusEnum.WRONG);
        easyContainingSome.add(GuessDigitStatusEnum.WRONG);
        assertEquals(easyContainingSome, result.calculateGuessDigitStatus(GameMode.EASY, "123", "356"));
    }

    @Test
    public void testEasyContainingSome2() { // contains 2nd digit
        List<GuessDigitStatusEnum> easyContainingSome = new ArrayList<>();
        easyContainingSome.add(GuessDigitStatusEnum.WRONG);
        easyContainingSome.add(GuessDigitStatusEnum.CONTAINING_NUMBER);
        easyContainingSome.add(GuessDigitStatusEnum.WRONG);
        assertEquals(easyContainingSome, result.calculateGuessDigitStatus(GameMode.EASY, "123", "416"));
    }

    @Test
    public void testEasyContainingSome3() { // contains 3rd digit
        List<GuessDigitStatusEnum> easyContainingSome = new ArrayList<>();
        easyContainingSome.add(GuessDigitStatusEnum.WRONG);
        easyContainingSome.add(GuessDigitStatusEnum.WRONG);
        easyContainingSome.add(GuessDigitStatusEnum.CONTAINING_NUMBER);
        assertEquals(easyContainingSome, result.calculateGuessDigitStatus(GameMode.EASY, "123", "452"));
    }

    @Test
    public void testEasyCorrect1() { // 1st digit is correct
        List<GuessDigitStatusEnum> easyCorrect = new ArrayList<>();
        easyCorrect.add(GuessDigitStatusEnum.CORRECT_PLACEMENT);
        easyCorrect.add(GuessDigitStatusEnum.WRONG);
        easyCorrect.add(GuessDigitStatusEnum.WRONG);
        assertEquals(easyCorrect, result.calculateGuessDigitStatus(GameMode.EASY, "123", "156"));
    }

    @Test
    public void testEasyCorrect2() { // 2nd digit is correct
        List<GuessDigitStatusEnum> easyCorrect = new ArrayList<>();
        easyCorrect.add(GuessDigitStatusEnum.CORRECT_PLACEMENT);
        easyCorrect.add(GuessDigitStatusEnum.WRONG);
        easyCorrect.add(GuessDigitStatusEnum.WRONG);
        assertEquals(easyCorrect, result.calculateGuessDigitStatus(GameMode.EASY, "123", "426"));
    }

    @Test
    public void testEasyCorrect3() { // 3rd digit is correct
        List<GuessDigitStatusEnum> easyCorrect = new ArrayList<>();
        easyCorrect.add(GuessDigitStatusEnum.CORRECT_PLACEMENT);
        easyCorrect.add(GuessDigitStatusEnum.WRONG);
        easyCorrect.add(GuessDigitStatusEnum.WRONG);
        assertEquals(easyCorrect, result.calculateGuessDigitStatus(GameMode.EASY, "123", "453"));
    }
}
