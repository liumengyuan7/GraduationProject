package com.pm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.service.CalendarService;

@Controller
@RequestMapping("calendar")
public class CalendarController {
	@Resource
	private CalendarService calendarService;
	
	/**
	 * 查询当前日的相关信息
	 * @param date 指定日期,格式为YYYY-MM-DD,如月份和日期小于10,则取个位,如:2012-1-1
	 * @return
	 */
	@RequestMapping(value="findCalendar",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findCalendar(@RequestParam("date") String date) {
		String result = this.calendarService.findCalendar(date);
		return result;
	}
	
	/**
	 * 查看历史上的今天
	 * @param date日期,格式:月/日 如:1/1,10/1,12/12 如月或者日小于10,前面无需加0
	 * @return
	 */
	@RequestMapping(value="historyToday",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String historyToday(@RequestParam("date") String date) {
		String result = this.calendarService.historyToday(date);
		return result;
	}
	
	/**
	 * 历史上的今天详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="historyTodayDetail",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String historyTodayDetail(@RequestParam("e_id") String id) {
		String result = this.calendarService.historyTodayDetail(id);
		return result;
	}
	
	/**
	 * 今日天气
	 * @param city 城市名
	 * @return
	 */
	@RequestMapping(value="todayWeather",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String todayWeather(@RequestParam("city") String city) {
		String result = this.calendarService.todayWeather(city);
		return result;
	}
}
