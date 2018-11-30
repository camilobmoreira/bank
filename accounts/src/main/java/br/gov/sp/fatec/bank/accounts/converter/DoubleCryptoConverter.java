package br.gov.sp.fatec.bank.accounts.converter;

public class DoubleCryptoConverter extends AbstractCryptoConverter<Double> {

    public DoubleCryptoConverter() {
        this(new CipherInitializer());
    }

    public DoubleCryptoConverter(CipherInitializer cipherInitializer) {
        super(cipherInitializer);
    }

    @Override
    boolean isNotNullOrEmpty(Double attribute) {
        return attribute != null;
    }

    @Override
    Double stringToEntityAttribute(String dbData) {
        return Double.parseDouble(dbData);
    }

    @Override
    String entityAttributeToString(Double attribute) {
        return attribute == null ? "0.0" : attribute.toString();
    }
}
