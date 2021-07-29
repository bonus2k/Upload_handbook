<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <div class="container">
        <div class="row">
            <div class="col-6">
                <b>Настройка БД:</b>
                <form method="post" action="/settings_db">

                    <div class="p-2 mt-2 col-12 border border-secondary rounded g-2">

                        <#if alertBil??>
                            <div class="alert alert-${alertBil}" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true"><i class="fal fa-times-circle fa-xs"></i></span>
                                </button>
                                <div class="w-80 text"><p class="font-weight-light">${messageBil}</p></div>
                            </div>
                        </#if>


                        <div class="p-2 input-group input-group-sm col-md-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Тип БД: </span>
                            </div>
                            <select class="form-control" name=billing.jdbc.driverClassName>
                                <option selected>${driverApp}</option>
                                <#list drivers as driver>
                                    <option value=${driver}>${driver}</option>
                                </#list>
                            </select>
                        </div>


                        <div class="p-2 input-group input-group-sm col-md-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-sm">URL БД: </span>
                            </div>
                            <input type="text" id="" class="form-control" placeholder="URL БД"
                                   aria-label="URL БД" name=billing.jdbc.url value="${billingURL}">
                        </div>

                        <div class="p-2 input-group input-group-sm col-md-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Логин:&nbsp;&nbsp;</span>
                            </div>
                            <input type="text" id="" class="form-control" placeholder="Логин"
                                   aria-label="Логин" name=billing.jdbc.username value="${settingsAppDB.username}">
                        </div>


                        <div class="p-2 input-group input-group-sm col-md-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Пароль: </span>
                            </div>
                            <input type="password" id="" class="form-control" placeholder="Пароль"
                                   aria-label="Пароль" name=billing.jdbc.password value="${settingsAppDB.password}">
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="billing.jdbc.driverClassName" value="${driver!}"/>
                        <input type="hidden" name="billing.jdbc.url" value="${url!}"/>
                        <input type="hidden" name="billing.jdbc.username" value="${login!}"/>
                        <input type="hidden" name="billing.jdbc.password" value="${password!}"/>


                        <div class="p-2 row">
                            <div class="col-9"></div>
                            <div class="input-group-sm col-3">
                                <button class="form-control">Сохранить</button>
                            </div>
                        </div>


                    </div>

                </form>
            </div>
            <div class="col-6">
                <b>Настройка FTP:</b>
                <form method="post" action="/settings_ftp">

                    <div class="p-2 mt-2 col-12 border border-secondary rounded g-2">

                        <#if alertFtp??>
                            <div class="alert alert-${alertFtp}" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">  <i class="fal fa-times-circle fa-xs"></i></span>
                                </button>
                                <div class="w-80 text"><p class="font-weight-light">${messageFtp}</p></div>
                            </div>
                        </#if>

                        <div class="p-2 input-group input-group-sm col-md-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Адрес:&nbsp;&nbsp; </span>
                            </div>
                            <input type="text" id="" class="form-control" placeholder="Адрес FTP сервера"
                                   aria-label="Адрес FTP сервера" name=FtpServer value="${settingsAppFTP.ftpServer!}">
                        </div>


                        <div class="p-2 input-group input-group-sm col-md-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text"
                                      id="inputGroup-sizing-sm">Порт:&nbsp;&nbsp;&nbsp;&nbsp; </span>
                            </div>
                            <input type="text" id="" class="form-control" placeholder="Порт FTP сервера"
                                   aria-label="Порт FTP сервера" name=FtpPort value="${settingsAppFTP.ftpPort!}">
                        </div>

                        <div class="p-2 input-group input-group-sm col-md-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Логин:&nbsp;&nbsp;</span>
                            </div>
                            <input type="text" id="" class="form-control" placeholder="Логин"
                                   aria-label="Логин" name=FtpUser value="${settingsAppFTP.ftpUser!}">
                        </div>


                        <div class="p-2 input-group input-group-sm col-md-12">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Пароль: </span>
                            </div>
                            <input type="password" id="" class="form-control" placeholder="Пароль"
                                   aria-label="Пароль" name=FtpPassword value="${settingsAppFTP.ftpPassword!}">
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="FtpServer" value="${FtpServer!}"/>
                        <input type="hidden" name="FtpPort" value="${FtpPort!}"/>
                        <input type="hidden" name="FtpUser" value="${FtpUser!}"/>
                        <input type="hidden" name="FtpPassword" value="${FtpPassword!}"/>


                        <div class="p-2 row">
                            <div class="col-9"></div>
                            <div class="input-group-sm col-3">
                                <button class="form-control">Сохранить</button>
                            </div>
                        </div>

                    </div>

                </form>
            </div>
        </div>
    </div>
</@c.page>