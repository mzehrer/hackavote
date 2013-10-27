package org.zepan.hackavote.domain;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 27.10.13
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class Error {

    private String message;

    public Error() {
    }

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
