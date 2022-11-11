package com.example.fifaqatar2022.Classes;

public enum Group_enum {
    A, B, C, D, E, F, G, H;

    static public int converter(Group_enum val) {
        switch (val) {
            case A:
                return 0;
            case B:
                return 1;
            case C:
                return 2;
            case D:
                return 3;
            case E:
                return 4;
            case F:
                return 5;
            case G:
                return 6;
            case H:
                return 7;
            default:
                return -1;
        }
    }
}



