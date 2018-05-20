package hu.edu.elte.codeguesser_model;

/**
 * Represents the status of the individual digits of a guess.
 *
 * @author virabia on 5/10/2018
 */
public enum GuessDigitStatusEnum {
    /**
     * The digit is placed correctly in the guess. (e.g. original is 123 guess is 425 then the 2 is in this status.)
     * This status contains the 'CONTAINING_NUMBER' status as well.
     */
    CORRECT_PLACEMENT,

    /**
     * The digit is present in the original number as well.
     * (e.g. original is 123 guess is 345 then the 3 is in this status.)
     */
    CONTAINING_NUMBER,

    /**
     * The digit is not present in the original number.
     * (e.g. original is 123 guess is 325 then the 5 is in this status.)
     */
    WRONG,

    /**
     * This is the default status, when a guess has been made.
     * This indicates that the digit with this status has not yet been validated.
     */
    UNKNOWN
    ;
}
