package app.services;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Data
public class AuthenticationService implements Authentication {
    private Map<String, String> userProfileData = new ConcurrentHashMap<>();
    {
        userProfileData.put("name1", DigestUtils.md5Hex("1234"));

    }
    @Override
    public boolean isUserNamePresent(String userName) {
        return userProfileData.containsKey(userName);
    }

    @Override
    public boolean isUserAuthentication(String userName, String password) {
        if(!isUserNamePresent(userName)){
            return false;
        }
        return userProfileData.get(userName).equals(DigestUtils.md5Hex(password));
    }
}
