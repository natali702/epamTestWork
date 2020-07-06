package app.services;

public interface Authentication {
    boolean isUserPresent(String username);
    boolean isUserAuthenticated(String username, String password);
    void setUserData(String username, String password);
}
