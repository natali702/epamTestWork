package app.services;

public interface Authentication {
    boolean isUserNamePresent(String userName);
    boolean isUserAuthentication(String userName, String password);
}
