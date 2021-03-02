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
}
