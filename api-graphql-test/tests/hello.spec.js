import axios from 'axios';
import { XMLHttpRequest } from 'xmlhttprequest';

global.XMLHttpRequest = XMLHttpRequest;

describe('hellos', () => {
  test('hellos', async () => {
    // 执行调用api
    const response = await axios.post('http://localhost:9999/graphql', {
      query: `
        {
          sum:（1,2)
        }
      `,
    });

    //获取实际结果
    const { data } = response;
    // expect(data).toMatchObject({
    //   data: {
    //     hellos: [],
    //   },
    // });
    //将实际结果与预期比较， 比较结果要么true，要么false
    expect(data).toEqual(3)
    );
  });

});
