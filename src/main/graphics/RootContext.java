package main.graphics;

import javafx.scene.Group;

/**
Root Group Singleton
@return currentContext Group
 **/

public class RootContext {
    private static Group instance = new Group();

    public static Group getInstance() {

        return instance;
    }

    private RootContext() {
    }
}
