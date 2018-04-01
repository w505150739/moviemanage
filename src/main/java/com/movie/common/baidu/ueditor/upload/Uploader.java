package com.movie.common.baidu.ueditor.upload;

import com.movie.common.baidu.ueditor.define.State;
import com.movie.common.config.DeployUtil;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;
	private DeployUtil deployUtil;

	public Uploader(HttpServletRequest request, Map<String, Object> conf,DeployUtil deployUtil) {
		this.request = request;
		this.conf = conf;
		this.deployUtil = deployUtil;
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf);
		} else {
			state = BinaryUploader.save(this.request, this.conf,deployUtil);
		}

		return state;
	}
}
