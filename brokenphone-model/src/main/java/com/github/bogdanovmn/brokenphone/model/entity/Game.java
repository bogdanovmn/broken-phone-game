package com.github.bogdanovmn.brokenphone.model.entity;

import com.github.bogdanovmn.common.spring.jpa.BaseEntity;

import java.util.List;

class Game extends BaseEntity {
	private List<User> users;
	private PhraseTemplate phraseTemplate;
}
