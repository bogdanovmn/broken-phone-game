package com.github.bogdanovmn.brokenphone.model.entity;

import com.github.bogdanovmn.common.spring.jpa.BaseEntity;

import java.util.List;

class PhraseTemplate extends BaseEntity {
	private List<Question> questions;
}
