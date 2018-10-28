/*
 * Copyright 2018 Data Whisperers LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datawhisperers.kafkaconsumermeetup.pojo;

import java.util.ArrayList;

public class Group {

    private String group_city;
    private String group_country;
    private String group_id;
    private String group_name;
    private String group_lon;
    private String group_urlname;
    private String group_state;
    private String group_lat;
    private ArrayList<GroupTopic> group_topics;

    public String getGroup_city() {
        return group_city;
    }

    public void setGroup_city(String group_city) {
        this.group_city = group_city;
    }

    public String getGroup_country() {
        return group_country;
    }

    public void setGroup_country(String group_country) {
        this.group_country = group_country;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_lon() {
        return group_lon;
    }

    public void setGroup_lon(String group_lon) {
        this.group_lon = group_lon;
    }

    public String getGroup_urlname() {
        return group_urlname;
    }

    public void setGroup_urlname(String group_urlname) {
        this.group_urlname = group_urlname;
    }

    public String getGroup_state() {
        return group_state;
    }

    public void setGroup_state(String group_state) {
        this.group_state = group_state;
    }

    public String getGroup_lat() {
        return group_lat;
    }

    public void setGroup_lat(String group_lat) {
        this.group_lat = group_lat;
    }

    public ArrayList<GroupTopic> getGroup_topics() {
        return group_topics;
    }

    public void setGroup_topics(ArrayList<GroupTopic> group_topics) {
        this.group_topics = group_topics;
    }

    @Override
    public String toString() {
        return "Group{" + "groupCity=" + group_city + ", groupCountry=" + group_country + ", groupId=" + group_id + ", groupName=" + group_name + ", groupLon=" + group_lon + ", groupUrlname=" + group_urlname + ", groupState=" + group_state + ", groupLat=" + group_lat + ", groupTopics=" + group_topics + '}';
    }

}
