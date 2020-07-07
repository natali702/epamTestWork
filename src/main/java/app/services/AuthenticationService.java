package app.services;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Data
public class AuthenticationService implements Authentication {

    private Map<String, String> userProfileData = new ConcurrentHashMap<>();


    @Override
    public boolean isUserPresent(String username) {
        return userProfileData.containsKey(username);
    }

    @Override
    public boolean isUserAuthenticated(String username, String password) {
        if(!isUserPresent(username)){
            return false;
        }
        return userProfileData.get(username).equals(DigestUtils.md5Hex(password));
    }

    @Override
    public void setUserData(String username, String password) {
        userProfileData.put(username, DigestUtils.md5Hex(password));
    }
}
