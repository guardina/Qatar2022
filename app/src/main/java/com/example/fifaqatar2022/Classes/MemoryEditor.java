package com.example.fifaqatar2022.Classes;

public class MemoryEditor {

    static MemoryEditor memoryEditor = null;

    MemoryEditor() {}

    static public MemoryEditor getMemoryEditor() {
        if (memoryEditor == null) {
            return new MemoryEditor();
        }
        return memoryEditor;
    }

}
