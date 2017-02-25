package com.kllect

/**
 * Created by mingliangma on 2017-02-25.
 */
enum HiddenStatus {
    NOT_HIDDEN(0), CORRUPT(1), MARKETING(2), UNRELATED_CATEGORY(3)

    private final int status

    HiddenStatus (int status){
        this.status = status
    }

    int getStatus(){
        status
    }
}