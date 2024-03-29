package com.xkd.myapplication;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.data.DatabaseHelper;
import ohos.data.rdb.RdbOpenCallback;
import ohos.data.rdb.RdbStore;
import ohos.data.rdb.StoreConfig;
import ohos.data.resultset.ResultSet;
import ohos.data.rdb.ValuesBucket;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.net.Uri;
import ohos.utils.PacMap;

import java.io.FileDescriptor;

public class DataAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private RdbStore rdbStore;
    private StoreConfig config = StoreConfig.newDefaultConfig("Mall.db");

    private RdbOpenCallback openCallback = new RdbOpenCallback() {
        @Override
        public void onCreate(RdbStore rdbStore) {
            //如果数据表不存在，需要先创建数据表
            rdbStore.executeSql("create table if not exists fmmall_info(id integer primary key autoincrement,key text not null unique,value text not null)");
        }
        public void onUpgrade(RdbStore rdbStore, int i, int i1) {}
    };
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        rdbStore = databaseHelper.getRdbStore(config,1,openCallback);
        HiLog.info(LABEL_LOG, "DataAbility onStart");
    }

    @Override
    public ResultSet query(Uri uri, String[] columns, DataAbilityPredicates predicates) {
        return null;
    }

    @Override
    public int insert(Uri uri, ValuesBucket value) {
        HiLog.info(LABEL_LOG, "DataAbility insert");
        return 999;
    }

    @Override
    public int delete(Uri uri, DataAbilityPredicates predicates) {
        return 0;
    }

    @Override
    public int update(Uri uri, ValuesBucket value, DataAbilityPredicates predicates) {
        return 0;
    }

    @Override
    public FileDescriptor openFile(Uri uri, String mode) {
        return null;
    }

    @Override
    public String[] getFileTypes(Uri uri, String mimeTypeFilter) {
        return new String[0];
    }

    @Override
    public PacMap call(String method, String arg, PacMap extras) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}