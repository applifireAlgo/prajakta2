




echo $PATH
DB_PATH=/tmp/applifire/db/AFD74JF8P8ZNVOT3ZQMBNA/DEED06AD-6A79-470A-9647-FA8B40CA0FD1
MYSQL=/usr/bin
USER=algo
PASSWORD=algo
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'