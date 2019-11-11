package service;

import model.UserModel;

public class UserCreator {
    public static final String TESTDATA_USER_NAME = "testdata.username";
    public static final String TESTDATA_USER_PASSWORD = "testdata.password";

    public static UserModel withCredentialsFromProperty(){
        return new UserModel(TestDataReader.getTestData(TESTDATA_USER_NAME),TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }
}
