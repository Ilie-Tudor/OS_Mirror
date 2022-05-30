package Stores;

import Application.BaseApplication;

import java.util.Vector;

public interface BaseStore {
    void exportAllFromMemory(MemoryStore ms);
    MemoryStore importAllIntoMemory();
}
