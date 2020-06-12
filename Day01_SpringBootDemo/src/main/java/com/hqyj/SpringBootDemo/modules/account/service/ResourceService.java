package com.hqyj.SpringBootDemo.modules.account.service;

import com.hqyj.SpringBootDemo.modules.account.entity.Resource;
import com.hqyj.SpringBootDemo.modules.common.vo.Result;

public interface ResourceService {
	
	Result<Resource> insertResource(Resource resource);
	
}
