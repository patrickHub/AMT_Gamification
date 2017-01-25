// Testing badgeachievements/
// --------------------------

// Utils
let chai = require('chai');
let chaiHttp = require('chai-http');
chai.use(chaiHttp);
let CONFIG = require('./config');
let shared = require('./shared');
let Utils = require('./utils');

describe('badgeachievements/', function () {

    beforeEach(function() {
        if(shared.achievement.length < 2
            || shared.badge.length < 2) 
            this.skip();
    });

    describe('POST', function () {
        
        it('should allow to create a new badgeachievement', function (done) {
            chai.request(CONFIG.API)
                .post('badgeachievements/')
                .set('content-type', 'application/json')
                .set('authorization', shared.token)
                .send({
                    achievement_id: shared.achievement[0].id,
                    badge_id: shared.badge[0].id
                })
                .then(function (res) {
                    Utils.debug('res', res);
                    chai.expect(res).to.not.be.undefined;
                    chai.expect(res).to.have.status(201);
                    chai.expect(res).to.have.property('text');
                    shared.badgeachievement.push(JSON.parse(res.text));
                    done();
                })
                .catch(function(err) {
                    Utils.debug('err', err);
                    done(err);
                });
        });

        it('should allow to create many badgeachievements', function (done) {
            chai.request(CONFIG.API)
                .post('badgeachievements/')
                .set('content-type', 'application/json')
                .set('authorization', shared.token)
                .send({
                    achievement_id: shared.achievement[1].id,
                    badge_id: shared.badge[1].id
                })
                .then(function (res) {
                    Utils.debug('res', res);
                    chai.expect(res).to.not.be.undefined;
                    chai.expect(res).to.have.status(201);
                    chai.expect(res).to.have.property('text');
                    chai.expect(JSON.parse(res.text)).to.not.equal(shared.badgeachievement[0]);
                    shared.badgeachievement.push(JSON.parse(res.text));
                    done();
                })
                .catch(function(err) {
                    Utils.debug('err', err);
                    done(err);
                });
        });

        it('should not allow to create two badgeachievements with the same IDs', function (done) {
            chai.request(CONFIG.API)
                .post('badgeachievements/')
                .set('content-type', 'application/json')
                .set('authorization', shared.token)
                .send({
                    achievement_id: shared.achievement[0].id,
                    badge_id: shared.badge[0].id
                })
                .end(function(err, res) { 
                    Utils.debug('err', err);
                    chai.expect(err).to.not.be.undefined;
                    chai.expect(err).to.have.status(409);
                    done();
                });
        });

        it('should not allow an invalid achievement_id', function (done) {
            chai.request(CONFIG.API)
                .post('badgeachievements/')
                .set('content-type', 'application/json')
                .set('authorization', shared.token)
                .send({
                    achievement_id: 98283,
                    badge_id: shared.badge[0].id
                })
                .end(function(err, res) { 
                    Utils.debug('err', err);
                    chai.expect(err).to.not.be.undefined;
                    chai.expect(err).to.have.status(400);
                    done();
                });
        });

        it('should not allow an invalid badge_id', function (done) {
            chai.request(CONFIG.API)
                .post('badgeachievements/')
                .set('content-type', 'application/json')
                .set('authorization', shared.token)
                .send({
                    achievement_id: shared.achievement[0].id,
                    badge_id: 3209
                })
                .end(function(err, res) { 
                    Utils.debug('err', err);
                    chai.expect(err).to.not.be.undefined;
                    chai.expect(err).to.have.status(400);
                    done();
                });
        });

        // malformed payloads
        Utils.generateMalformed({
            achievement_id: 0,
            badge_id: 0
        }).forEach(function(malformed) {

            it('should refuse a malformed payload (' + malformed.what + ')', function (done) {
                chai.request(CONFIG.API)
                    .post('badgeachievements/')
                    .set('content-type', 'application/json')
                    .set('authorization', shared.token)
                    .send(malformed)
                    .end(function(err, res) { 
                        Utils.debug('err', err);
                        chai.expect(err).to.not.be.undefined;
                        chai.expect(err).to.have.status(400);
                        done();
                    });
            });
        });
    });

    describe('GET', function () {

        beforeEach(function() {
            if(shared.badgeachievement.length < 2) 
                this.skip();
        });
        
        it('should return an array of created badgeachievements', function (done) {
            chai.request(CONFIG.API)
                .get('badgeachievements/')
                .set('content-type', 'application/json')
                .set('authorization', shared.token)
                .then(function (res) {
                    Utils.debug('res', res);
                    chai.expect(res).to.not.be.undefined;
                    chai.expect(res).to.have.status(200);
                    chai.expect(res).to.have.property('text');
                    chai.expect(JSON.parse(res.text)).to.have.lenght(2);
                    chai.expect(JSON.parse(res.text)[0].achievement_id).to.be.equal.to(shared.achievement[0].id);
                    chai.expect(JSON.parse(res.text)[0].badge_id).to.be.equal.to(shared.badge[0].id);
                    chai.expect(JSON.parse(res.text)[1].achievement_id).to.be.equal.to(shared.achievement[1].id);
                    chai.expect(JSON.parse(res.text)[1].badge_id).to.be.equal.to(shared.badge[1].id);
                    done();
                })
                .catch(function(err) {
                    Utils.debug('err', err);
                    done(err);
                });
        });
    });

    describe('badgeachievements/{id}/', function () {

        beforeEach(function() {
            if(shared.badgeachievement.length < 2) 
                this.skip();
        });
        
        describe('GET', function () {
            
            it('should return a specifiy badgeachievement', function (done) {
                chai.request(CONFIG.API)
                    .get('badgeachievements/' + shared.badgeachievement[0].id + '/')
                    .set('content-type', 'application/json')
                    .set('authorization', shared.token)
                    .then(function (res) {
                        Utils.debug('res', res);
                        chai.expect(res).to.not.be.undefined;
                        chai.expect(res).to.have.status(200);
                        chai.expect(res).to.have.property('text');
                        chai.expect(JSON.parse(res.text).achievement_id).to.be.equal.to(shared.achievement[0].id);
                        chai.expect(JSON.parse(res.text).badge_id).to.be.equal.to(shared.badge[0].id);
                        done();
                    })
                    .catch(function(err) {
                        Utils.debug('err', err);
                        done(err);
                    });
            });

            it('should not allow an undefined ID', function (done) {
                chai.request(CONFIG.API)
                    .get('badgeachievements/7834/')
                    .set('content-type', 'application/json')
                    .set('authorization', shared.token)
                    .end(function(err, res) { 
                        Utils.debug('err', err);
                        chai.expect(err).to.not.be.undefined;
                        chai.expect(err).to.have.status(404);
                        done();
                    });
            });
        });
    });
});