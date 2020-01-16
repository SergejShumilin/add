package com.epam.esm.dao.exception;


public class CertificateNotFoundException extends RuntimeException {

    public CertificateNotFoundException(int id){
        super(String.valueOf(id));
    }

    public CertificateNotFoundException(String message) {
        super(message);
    }

}
