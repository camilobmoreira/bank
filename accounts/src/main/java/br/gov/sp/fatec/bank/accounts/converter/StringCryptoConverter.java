package br.gov.sp.fatec.bank.accounts.converter;

import javax.persistence.Converter;
@Converter
public class StringCryptoConverter extends AbstractCryptoConverter<String> {

    public StringCryptoConverter() {
        this(new CipherInitializer());
    }

    public StringCryptoConverter(CipherInitializer cipherInitializer) {
        super(cipherInitializer);
    }

    @Override
    boolean isNotNullOrEmpty(String attribute) {
        return !attribute.isEmpty();
    }

    @Override
    String stringToEntityAttribute(String dbData) {
        return dbData;
    }

    @Override
    String entityAttributeToString(String attribute) {
        return attribute;
    }
}