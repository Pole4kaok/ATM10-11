package service;

import model.UserModel;

public class UserCreator {
    public static final String TESTDATA_USER_NAME = "testdata.username";
    public static final String TESTDATA_USER_PASSWORD = "testdata.password";

    public static UserModel withCredentialsFromProperty(){
        return new UserModel(new TestDataReader().getTestData(TESTDATA_USER_NAME),new TestDataReader().getTestData(TESTDATA_USER_PASSWORD));
    }
}
