package tech.codingclub.helix.entity;

public class TimeApi {
    //here we are creating the API to fetch the time

    private String time_str;
    private Long epoch_time;// epoch time is total milli sec till now from 1900


    public TimeApi() {
    }

    public TimeApi(String time_str, Long epoch_time) {
        this.time_str = time_str;
        this.epoch_time = epoch_time;
    }
}
