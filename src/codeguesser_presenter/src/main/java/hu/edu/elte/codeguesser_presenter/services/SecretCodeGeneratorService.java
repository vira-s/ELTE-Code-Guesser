package hu.edu.elte.codeguesser_presenter.services;

/**
 * @author virabia on 5/10/2018
 */
public interface SecretCodeGeneratorService {

    /**
     * Generates a secret code (numbers only) with the given length.
     *
     * @param codeLength The length of the code
     *
     * @return The code
     */
    String generateSecretCode(int codeLength);

}
