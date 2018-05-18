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
}
