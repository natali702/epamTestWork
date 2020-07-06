package app.model;

//@JsonPropertyOrder({ "id", "username", "age"})
public class User {
    private int id;
    private String username;
    private String login;
    private String password;
    private int passwordInt;
    private int age;



    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
//    public User(int id, String login, String password) {
//        this.id = id;
//        this.login = login;
//        this.password = password;
//    }
//
//    public User(String login, String password) {
//        this.login = login;
//        this.password = password;
//    }
    //    public User(int id, String username, int age) {
//        this.id = id;
//        this.username = username;
//        this.age = age;
//    }
//
//    public User(String username, int passwordInt) {
//        this.username = username;
//        this.passwordInt = passwordInt;
//    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPasswordInt() {
        return passwordInt;
    }

    public void setPasswordInt(int passwordInt) {
        this.passwordInt = passwordInt;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
}
