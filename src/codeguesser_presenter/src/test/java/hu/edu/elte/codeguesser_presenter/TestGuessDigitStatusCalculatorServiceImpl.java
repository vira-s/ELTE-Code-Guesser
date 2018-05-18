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

    @Test
    public void testMediumNotContainingAny() {
        List<GuessDigitStatusEnum> mediumNotContainingAny = new ArrayList<>();
        mediumNotContainingAny.add(GuessDigitStatusEnum.WRONG);
        mediumNotContainingAny.add(GuessDigitStatusEnum.WRONG);
        mediumNotContainingAny.add(GuessDigitStatusEnum.WRONG);
        assertEquals(mediumNotContainingAny, result.calculateGuessDigitStatus(GameMode.MEDIUM, "123", "456"));
    }

    @Test
    public void testMediumContainingSome1() { // contains 1st digit
        List<GuessDigitStatusEnum> mediumContainingSome = new ArrayList<>();
        mediumContainingSome.add(GuessDigitStatusEnum.CONTAINING_NUMBER);
        mediumContainingSome.add(GuessDigitStatusEnum.WRONG);
        mediumContainingSome.add(GuessDigitStatusEnum.WRONG);
        assertEquals(mediumContainingSome, result.calculateGuessDigitStatus(GameMode.MEDIUM, "123", "356"));
    }

    @Test
    public void testMediumContainingSome2() { // contains 2nd digit
        List<GuessDigitStatusEnum> mediumContainingSome = new ArrayList<>();
        mediumContainingSome.add(GuessDigitStatusEnum.WRONG);
        mediumContainingSome.add(GuessDigitStatusEnum.CONTAINING_NUMBER);
        mediumContainingSome.add(GuessDigitStatusEnum.WRONG);
        assertEquals(mediumContainingSome, result.calculateGuessDigitStatus(GameMode.MEDIUM, "123", "416"));
    }

    @Test
    public void testMediumContainingSome3() { // contains 3rd digit
        List<GuessDigitStatusEnum> mediumContainingSome = new ArrayList<>();
        mediumContainingSome.add(GuessDigitStatusEnum.WRONG);
        mediumContainingSome.add(GuessDigitStatusEnum.WRONG);
        mediumContainingSome.add(GuessDigitStatusEnum.CONTAINING_NUMBER);
        assertEquals(mediumContainingSome, result.calculateGuessDigitStatus(GameMode.MEDIUM, "123", "452"));
    }

    @Test
    public void testMediumCorrect1() { // 1st digit is correct
        List<GuessDigitStatusEnum> mediumCorrect = new ArrayList<>();
        mediumCorrect.add(GuessDigitStatusEnum.CONTAINING_NUMBER);
        mediumCorrect.add(GuessDigitStatusEnum.WRONG);
        mediumCorrect.add(GuessDigitStatusEnum.WRONG);
        assertEquals(mediumCorrect, result.calculateGuessDigitStatus(GameMode.MEDIUM, "123", "156"));
    }

    @Test
    public void testMediumCorrect2() { // 2nd digit is correct
        List<GuessDigitStatusEnum> mediumCorrect = new ArrayList<>();
        mediumCorrect.add(GuessDigitStatusEnum.WRONG);
        mediumCorrect.add(GuessDigitStatusEnum.CONTAINING_NUMBER);
        mediumCorrect.add(GuessDigitStatusEnum.WRONG);
        assertEquals(mediumCorrect, result.calculateGuessDigitStatus(GameMode.MEDIUM, "123", "426"));
    }

    @Test
    public void testMediumCorrect3() { // 3rd digit is correct
        List<GuessDigitStatusEnum> mediumCorrect = new ArrayList<>();
        mediumCorrect.add(GuessDigitStatusEnum.WRONG);
        mediumCorrect.add(GuessDigitStatusEnum.WRONG);
        mediumCorrect.add(GuessDigitStatusEnum.CONTAINING_NUMBER);
        assertEquals(mediumCorrect, result.calculateGuessDigitStatus(GameMode.MEDIUM, "123", "453"));
    }

    @Test
    public void testHardNotContainingAny() {
        List<GuessDigitStatusEnum> hardNotContainingAny = new ArrayList<>();
        hardNotContainingAny.add(GuessDigitStatusEnum.UNKNOWN);
        hardNotContainingAny.add(GuessDigitStatusEnum.UNKNOWN);
        hardNotContainingAny.add(GuessDigitStatusEnum.UNKNOWN);
        assertEquals(hardNotContainingAny, result.calculateGuessDigitStatus(GameMode.HARD, "123", "456"));
    }

    @Test
    public void testHardContainingSome() {
        List<GuessDigitStatusEnum> hardContainingSome = new ArrayList<>();
        hardContainingSome.add(GuessDigitStatusEnum.UNKNOWN);
        hardContainingSome.add(GuessDigitStatusEnum.UNKNOWN);
        hardContainingSome.add(GuessDigitStatusEnum.UNKNOWN);
        assertEquals(hardContainingSome, result.calculateGuessDigitStatus(GameMode.HARD, "123", "256"));
    }

    @Test
    public void testHardCorrect1() { // 1st digit is correct
        List<GuessDigitStatusEnum> hardCorrect = new ArrayList<>();
        hardCorrect.add(GuessDigitStatusEnum.CORRECT_PLACEMENT);
        hardCorrect.add(GuessDigitStatusEnum.UNKNOWN);
        hardCorrect.add(GuessDigitStatusEnum.UNKNOWN);
        assertEquals(hardCorrect, result.calculateGuessDigitStatus(GameMode.HARD, "123", "156"));
    }

    @Test
    public void testHardCorrect2() { // 2nd digit is correct
        List<GuessDigitStatusEnum> hardCorrect = new ArrayList<>();
        hardCorrect.add(GuessDigitStatusEnum.UNKNOWN);
        hardCorrect.add(GuessDigitStatusEnum.CORRECT_PLACEMENT);
        hardCorrect.add(GuessDigitStatusEnum.UNKNOWN);
        assertEquals(hardCorrect, result.calculateGuessDigitStatus(GameMode.HARD, "123", "426"));
    }

    @Test
    public void testHardCorrect3() { // 3rd digit is correct
        List<GuessDigitStatusEnum> hardCorrect = new ArrayList<>();
        hardCorrect.add(GuessDigitStatusEnum.UNKNOWN);
        hardCorrect.add(GuessDigitStatusEnum.UNKNOWN);
        hardCorrect.add(GuessDigitStatusEnum.CORRECT_PLACEMENT);
        assertEquals(hardCorrect, result.calculateGuessDigitStatus(GameMode.HARD, "123", "453"));
    }
}
