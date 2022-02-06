set @unchartedId= UUID();
set @witcherId= UUID();

insert into Game(id,display_name) values (@unchartedId,'Uncharted 4');
insert into Game(id,display_name) values (@witcherId,'Witcher - Wild Hunt');

insert into Achievement(id,display_name, description, icon, display_order,created_at, updated_at, game_id) values (UUID(),'One Last Time','Collect All The Trophies','https://i.psnprofiles.com/games/c733cc/trophies/1L61febd.png',1,now(),now(), @unchartedId);
insert into Achievement(id,display_name, description, icon, display_order,created_at, updated_at, game_id) values (UUID(),'Charted! - Explorer','Complete the game in explorer mode','https://i.psnprofiles.com/games/c733cc/trophies/2Md5a6de.png',4,now(),now(), @unchartedId);
insert into Achievement(id,display_name, description, icon, display_order,created_at, updated_at, game_id) values (UUID(),'Charted! - Light','Complete the game in light mode','https://i.psnprofiles.com/games/c733cc/trophies/3M03211c.png',4,now(),now(), @unchartedId);
insert into Achievement(id,display_name, description, icon, display_order,created_at, updated_at, game_id) values (UUID(),'Charted! - Crushing','Complete the game in crushing mode','https://i.psnprofiles.com/games/c733cc/trophies/6Mfef25d.png',3,now(),now(), @unchartedId);
insert into Achievement(id,display_name, description, icon, display_order,created_at, updated_at, game_id) values (UUID(),'Ludonarrative Dissonance','Defeat 1000 enemies','https://i.psnprofiles.com/games/c733cc/trophies/37M8787f5.png',2,now(),now(), @unchartedId);