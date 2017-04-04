INSERT INTO type (id, type, name, "order") VALUES (1, 1, '湘菜', 1);
INSERT INTO type (id, type, name, "order") VALUES (2, 2, '川菜', 2);
INSERT INTO type (id, type, name, "order") VALUES (3, 3, '粤菜', 3);
INSERT INTO type (id, type, name, "order") VALUES (4, 4, '鲁菜', 4);
INSERT INTO type (id, type, name, "order") VALUES (5, 5, '浙菜', 5);
INSERT INTO type (id, type, name, "order") VALUES (6, 6, '徽菜', 6);
INSERT INTO type (id, type, name, "order") VALUES (7, 7, '闽菜', 7);
INSERT INTO type (id, type, name, "order") VALUES (8, 8, '苏菜', 8);
INSERT INTO category (id, category, parent_category, image, name, "order") VALUES (1, 1, 0, '肉类.jpg', '肉类', 1);
INSERT INTO category (id, category, parent_category, image, name, "order") VALUES (2, 2, 0, '鱼类水产.jpg', '鱼类水产', 2);
INSERT INTO category (id, category, parent_category, image, name, "order") VALUES (3, 3, 0, '蛋类.jpg', '蛋类', 3);
INSERT INTO category (id, category, parent_category, image, name, "order") VALUES (4, 4, 0, '豆类.jpg', '豆类', 4);
INSERT INTO category (id, category, parent_category, image, name, "order") VALUES (5, 5, 0, '蔬菜.jpg', '蔬菜', 5);
INSERT INTO category (id, category, parent_category, image, name, "order") VALUES (6, 6, 0, '汤类.jpg', '汤类', 6);
INSERT INTO category (id, category, parent_category, image, name, "order") VALUES (7, 7, 0, '主食.jpg', '主食', 7);
INSERT INTO cuisine (id, type, name, ingredients, cover_image, banner_image, step_image, steps) VALUES (1, 1, '剁椒鱼头', '雄鱼头:1个;食用油:适量;盐:适量;剁辣椒:60g;葱:适量;姜:适量;蒜:适量', 'banner.jpg', 'banner.jpg', 'step1.jpg;step2.jpg;step3.jpg;step4.jpg;step5.jpg;step6.jpg;null', '姜切末、 蒜子切末、 葱切葱花。准备剁辣椒， 放入切好的姜、蒜拌匀。准备顶部切开、 腹部相连的雄鱼头，两面鱼肉各划一刀 ，鱼头各砍一刀。准备鱼头 ，淋入少量白酒， 放适量盐，拌匀， 腌制 30分钟 。准备盘 ，放入葱（整棵）垫底，再将鱼头切面朝下摆入盘中，均匀铺一层剁椒，再淋入适量茶油。放入蒸锅 ，盖上锅盖 ，水开后 ，隔水蒸 12分钟， 取出撒上葱花。锅入茶油， 烧热，淋在鱼上即可品尝！');
INSERT INTO cuisine (id, type, name, ingredients, cover_image, banner_image, step_image, steps) VALUES (2, 1, '辣椒炒肉', '五花肉:半斤;尖椒:200克;青蒜:1根;生抽:适量;盐:适量;鸡精:适量', 'banner.jpg', 'banner.jpg', 'step1.jpg;step2.jpg;step3.jpg;step4.jpg;step5.jpg;step6.jpg;step7.jpg;step8.jpg;step9.jpg', '五花肉半斤洗干净。将五花肉切片，用生抽将肉腌制。准备好辣椒并洗净，可以根据自己爱好选择尖椒或其他辣椒。将尖椒和青蒜切段。起油锅，下五花肉煸香并出出油。待五花肉八成熟时盛起待用。将辣椒，青蒜入锅煸炒。等到辣椒炒至表皮有点快糊的时候，下肉一起炒匀。待猪肉熟透后，加入盐、鸡精调味，就可以出锅了。');

