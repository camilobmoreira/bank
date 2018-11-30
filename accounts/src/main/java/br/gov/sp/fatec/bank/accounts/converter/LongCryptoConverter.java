package br.gov.sp.fatec.bank.accounts.converter;

import javax.persistence.Converter;

@Converter
public class LongCryptoConverter extends AbstractCryptoConverter<Long> {

    public LongCryptoConverter() {
        this(new CipherInitializer());
    }

    public LongCryptoConverter(CipherInitializer cipherInitializer) {
        super(cipherInitializer);
    }

    @Override
    boolean isNotNullOrEmpty(Long attribute) {
        return attribute != null;
    }

    @Override
    Long stringToEntityAttribute(String dbData) {
        return Long.parseLong(dbData);
    }

    @Override
    String entityAttributeToString(Long attribute) {
        return attribute == null ? "0" : attribute.toString();
    }
}