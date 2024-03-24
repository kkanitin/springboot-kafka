import http from 'k6/http';
import {check} from 'k6';

export const options = {
    stages: [
        {duration: "10s", target: 100},
        {duration: "60s", target: 100},
        {duration: "10s", target: 0},
    ],
};

const usernames = [
    'rainbow123',
    'beachlover',
    'techguru89',
    'adventureseeker',
    'greenapple22',
    'starlight77',
    'code_ninja',
    'musiclover99',
    'coffeeaddict56',
    'skywalker007'
];

const params = {
    headers: {
        'Content-Type': 'application/json',
    },
};

export default function () {
    let payload = {
        bookId: Math.floor(Math.random() * 20) + 1,
        username: usernames[Math.floor(Math.random() * 10)]
    }
    const res = http.post('http://localhost:8081/order/kafka', JSON.stringify(payload), params);
    check(res, {
        'is status 200': (r) => r.status === 200,
    });
}
