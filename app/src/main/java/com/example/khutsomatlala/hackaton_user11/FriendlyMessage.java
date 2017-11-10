/**
 * Copyright Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.khutsomatlala.hackaton_user11;

import java.security.PrivateKey;
import java.util.Date;

public class FriendlyMessage {

    private String text;
    private String name;
    private int RateNumber;

    public int getRateNumber() {
        return RateNumber;
    }

    public void setRateNumber(int rateNumber) {
        RateNumber = rateNumber;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    private long messageTime;

//    //Like
//    private String like;
//    private int num;
//
//
//    public int getNum() {
//        return num;
//    }
//
//    public void setNum(int num) {
//        this.num = num;
//    }
//
//    public String getLike() {
//        return like;
//    }
//
//    public void setLike(String like) {
//        this.like = like;
//    }

    public FriendlyMessage() {
    }



    public FriendlyMessage(int RateNumber) {

        this.RateNumber = RateNumber;
    }

    public FriendlyMessage(String text, String name ) {
        this.text = text;
        this.name = name;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMessageTime() {
        return messageTime;
    }

}
