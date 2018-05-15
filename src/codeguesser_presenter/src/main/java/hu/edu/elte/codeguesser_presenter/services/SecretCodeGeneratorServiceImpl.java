package hu.edu.elte.codeguesser_presenter.services;

import java.util.Random;

/**
 * @author virabia on 5/12/2018
 */
public class SecretCodeGeneratorServiceImpl implements SecretCodeGeneratorService {

    @Override
    public String generateSecretCode(int codeLength) {
        return String.valueOf(codeLength < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, codeLength - 1)) - 1)
                + (int) Math.pow(10, codeLength - 1));
    }
}
